package ir.co.sadad.avatarapi.common;

import jakarta.xml.bind.DatatypeConverter;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;

/**
 * class for common method of convert
 */
public class Converter {

    /**
     * method for convert to inputStream
     *
     * @param file
     * @return
     */

    public static String decodeJWTBody(String JWTToken) {
        String[] split_string = JWTToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64();

        return new String(base64Url.decode(base64EncodedBody));
    }

    public static byte[] extractBytes(DataBuffer dataBuffer) {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);

        return bytes;
    }

    public static byte[] toByteArray(String file) {
        String base64Image = file.split(",")[1];
        return DatatypeConverter.parseBase64Binary(base64Image);
    }
    public static byte[] convertTo(String file) {
        return DatatypeConverter.parseBase64Binary(file);
    }
}
