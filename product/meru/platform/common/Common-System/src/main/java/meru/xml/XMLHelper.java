package meru.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import meru.sys.IOSystem;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLHelper {

    static DocumentBuilder getDocumentBuilder() {

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = null;

        try {

            docBuilder = docBuilderFactory.newDocumentBuilder();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return docBuilder;
    }

    public static Document parse(String xml) throws SAXException,
                                            IOException {

        byte[] xmlBytes = xml.getBytes();

        ByteArrayInputStream inputStream = null;

        try {

            inputStream = new ByteArrayInputStream(xmlBytes);
            return parse(inputStream);

        } finally {
            IOSystem.close(inputStream);
        }
    }

    public static Document parse(InputStream inputStream) throws SAXException,
                                                         IOException {

        DocumentBuilder docBuilder = getDocumentBuilder();
        docBuilder.setErrorHandler(new ErrorHandler() {

            @Override
            public void warning(SAXParseException exception) throws SAXException {
                throw exception;

            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                throw exception;

            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                throw exception;

            }

        });
        Document document = docBuilder.parse(inputStream);

        return document;
    }

    public static Document parse(File xmlFile) throws SAXException,
                                              IOException {

        DocumentBuilder docBuilder = getDocumentBuilder();
        Document document = docBuilder.parse(xmlFile);

        return document;
    }

    public static String toString(Document document) {
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        DOMSource source = new DOMSource(document);
        try {
            Transformer xformer = TransformerFactory.newInstance()
                                                    .newTransformer();
            xformer.setOutputProperty("encoding",
                                      "UTF-8");
            xformer.setOutputProperty("method",
                                      "xml");
            xformer.setOutputProperty("indent",
                                      "yes");

            xformer.transform(source,
                              result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }

    public static String toString(Node node) {
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        DOMSource source = new DOMSource(node);
        try {
            Transformer xformer = TransformerFactory.newInstance()
                                                    .newTransformer();
            xformer.setOutputProperty("omit-xml-declaration",
                                      "yes");

            xformer.transform(source,
                              result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return writer.toString();
    }
}
