package nmmu.wrap301.menu;
import nmu.wrpv301.Controller;
import org.w3c.dom.*;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MenuBuilderUtil extends Menu {
    public MenuBuilderUtil(String title) {
        super(title);
    }

    public static Menu build(String xfile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File fin = new File(xfile);
        Document doc = builder.parse(fin);
        Element root = doc.getDocumentElement();

        Controller controller = (Controller) Class.forName(root.getAttribute("controller")).newInstance();
        Menu menu = new Menu("Main Menu");
        menu = processMenu(menu, root.getFirstChild(), controller);
        return menu;
    }

    private static Menu processMenu(Menu menu, Node node, Controller controller) throws Exception {
        if(node != null) {
            String nodeName = node.getNodeName();
            if(!nodeName.equals("#text")) {
                if (nodeName.equals("choice")) {
                    String actionName = node.getAttributes().getNamedItem("action").getNodeValue();
                    menu.add(node.getAttributes().getNamedItem("text").getNodeValue(), () -> {
                        try {
                            controller.getClass().getMethod(actionName).invoke(controller);
                        } catch (Exception e) {
                            System.out.println("Could call getMethod..." + e.getMessage());
                        }
                    });
                    Node nextSibling = node.getNextSibling();
                    if (nextSibling != null)
                        processMenu(menu, node.getNextSibling(), controller);
                } else {
                    Node textOrTitle = node.getAttributes().getNamedItem("title");
                    if(textOrTitle == null)
                        textOrTitle = node.getAttributes().getNamedItem("text");
                    Menu submenu = new Menu(textOrTitle.getNodeValue());
                    processMenu(submenu, node.getFirstChild(), controller);
                    menu.add(textOrTitle.getNodeValue(), submenu);
                }
            }
            processMenu(menu, node.getNextSibling(), controller);
        }
        return menu;
    }
}
