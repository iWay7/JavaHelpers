package site.iway.javahelpers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author iWay
 */
public class XmlConverter {

    public static String toXml(Object object) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            marshaller.marshal(object, byteArrayOutputStream);
            byte[] stringData = byteArrayOutputStream.toByteArray();
            return new String(stringData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T fromXml(String xml, Class<T> classOfT) {
        try {
            JAXBContext context = JAXBContext.newInstance(classOfT);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            return (T) unmarshaller.unmarshal(byteArrayInputStream);
        } catch (Exception e) {
            return null;
        }
    }

}
