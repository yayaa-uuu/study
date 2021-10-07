package cn.tnar.msf.cm4.msg.dto.entity.fast;

import lombok.Data;

import java.util.List;

@Data
public class FastContentValue {
    private String formId;
    private String link;
    // 这里是一个数组，我们就抽象为List，属性名为text
    private List<TextInfo> text;

    private String yyyImg;
    private String yyyAge;
    private String pagepath;
    

}

