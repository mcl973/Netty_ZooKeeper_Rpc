package ZK_Netty_Rpc.Info;

public class TypeTranser {
    /**
     * 获取参数类型
     * @param type
     * @return
     */
    public static  Class getType(String type){
        switch (type.trim()){
            case "int":return int.class;
            case "int[]":return int[].class;
            case "long":return long.class;
            case "long[]":return long[].class;
            case "double":return double.class;
            case "double[]":return double[].class;
            case "float":return float.class;
            case "float[]":return float[].class;
            case "char":return char.class;
            case "char[]":return char[].class;
            case "byte":return  byte.class;
            case "byte[]":return  byte[].class;
            case "short":return short.class;
            case "short[]":return short[].class;
            case "boolean":return boolean.class;
            case "boolean[]":return boolean[].class;
            case "String":return String.class;
            case "String[]":return String[].class;
            case "void":return Void.class;
            default:return null;
        }
    }

    /**
     * 将String类型的参数列表，转换为对应的类型
     * @param object  类型
     * @param str   具体的值
     * @return
     */
    public static Object getRealObject(String object,String str){
        switch (object.trim()){
            case "int":return Integer.parseInt(str);
            case "[I":
                String[] split6 = str.split("##");
                int[] ints = new int[split6.length];
                for (int i = 0; i < split6.length; i++) {
                    ints[i] = Integer.parseInt(split6[i]);
                }
                return ints;
            case "long":return Long.parseLong(str);
            case "[J":
                String[] split5 = str.split("##");
                long[] longs = new long[split5.length];
                for (int i = 0; i < split5.length; i++) {
                    longs[i] = Long.parseLong(split5[i]);
                }
                return longs;
            case "double":return Double.parseDouble(str);
            case "[D":
                String[] split4 = str.split("##");
                double[] doubles = new double[split4.length];
                for (int i = 0; i < split4.length; i++) {
                    doubles[i] = Double.parseDouble(split4[i]);
                }
                return doubles;
            case "float":return Float.parseFloat(str);
            case "[F":
                String[] split3 = str.split("##");
                float[] floats = new float[split3.length];
                for (int i = 0; i < floats.length; i++) {
                    floats[i] = Float.parseFloat(split3[i]);
                }
                return floats;
            case "char":return str.toCharArray()[0];
            case "[C":
                return str.toCharArray();
            case "byte":return  str.getBytes();
            case "[B":
                String[] split2 = str.split("##");
                byte[] bytes = new byte[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    bytes[i] = Byte.parseByte(split2[i]);
                }
                return bytes;
            case "short":return Short.parseShort(str);
            case "[S":
                String[] split1 = str.split("##");
                short[] ss = new short[split1.length];
                for (int i = 0; i < split1.length; i++) {
                    ss[i] = Short.parseShort(split1[i]);
                }
                return ss;
            case "boolean":return str.equals("true");
            case "[Z":
                String[] split = str.split("##");
                boolean[] b = new boolean[split.length];
                for (int i = 0; i < split.length; i++) {
                    b[i] = split[i].equals("true");
                }
                return split;
            case "java.lang.String":return "\""+str+"\"";
            case "[Ljava.lang.String;":return str.split("##");
            case "java.lang.Void":return "";
            default:return null;
        }
    }
}
