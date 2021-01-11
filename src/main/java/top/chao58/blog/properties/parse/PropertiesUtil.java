package top.chao58.blog.properties.parse;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private final String propertiesPath;
    private final String propertyKey;
    private Properties properties = new Properties();

    public PropertiesUtil(String propertiesName) throws IOException {
        //blog.xxx
        this.propertyKey = "blog." + propertiesName;
        //properties/xxx.properties
        this.propertiesPath = new File("").getCanonicalPath() + "/properties/" + propertiesName + ".properties";
        System.out.println("准备加载配置文件：" + propertiesPath);
        FileInputStream inputStream = new FileInputStream(propertiesPath);
        //配置对象
        properties.load(inputStream);
    }

    public String convert(String value) {
        StringBuilder builder = new StringBuilder();
        char[] chars = value.toCharArray();
        for (char c : chars) {
            if (c == '_') {
                builder.append(".");
                continue;
            }
            builder.append(c);
        }
        return propertyKey + "." + builder.toString();
    }


    public String get(String key) {
        return (String) properties.get(convert(key));
    }

    public void set(String key, String value) {
        properties.setProperty(convert(key), value);
        FileOutputStream outputStream = null;
        try {
            //重写回文件中
            outputStream = new FileOutputStream(propertiesPath);
            properties.store(outputStream, "");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
