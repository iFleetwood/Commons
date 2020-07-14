package cc.kasumi.commons.util;

import java.util.*;

public class DataSavingUtil {

    public static List<String> transformerDataToList(Collection<TransformerData> collection) {
        List<String> list = new ArrayList<>();

        for (TransformerData entry : collection) {
            list.add(entry.getKey() + ":" + entry.getValue());
        }

        return list;
    }

    public static List<TransformerData> listToTransformerData(List<String> list) {
        List<TransformerData> transformerData = new ArrayList<>();

        for (String string : list) {
            transformerData.add(new TransformerData() {
                String[] array = string.split(":");

                @Override
                public String getKey() {
                    return array[0];
                }

                @Override
                public String getValue() {
                    return array[1];
                }
            });
        }

        return transformerData;
    }
}
