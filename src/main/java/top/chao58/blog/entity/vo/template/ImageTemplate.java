package top.chao58.blog.entity.vo.template;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用于页面渲染
 * 天呐，我真不知道我是怎么想的，居然手写的把一句一句的给复制过来
 * 累啊类
 */
@Data
public class ImageTemplate {

    private String deleteUrl;
    private String pictureUrl;
    private String pictureName;


    /**
     * 构建图片模板
     */
    public String build() {
         /*
    以下都是模板
     */
        String imgStart = "<img class=\"picture\" ";
        String imgEnd = ">";

        String oncontextmenuStart = "oncontextmenu=\"deletePicture('";
        String oncontextmenuEnd = "')\"";

        String srcStart = "src = \"";
        String srcEnd = "\" ";

        String altStart = " alt = \"";
        String altEnd = "\"";

        return imgStart +
                oncontextmenuStart +
                deleteUrl +
                oncontextmenuEnd +
                srcStart +
                pictureUrl +
                srcEnd +
                altStart +
                pictureName +
                altEnd +
                imgEnd;
    }

}
