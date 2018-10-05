package site.iway.javahelpers;

public class Tester {

//    public static class Father implements Serializable {
//
//        public int a = 0;
//
//    }
//
//    public static class Son extends Father {
//
//        public int b = 0;
//
//    }


    private static class AAA {

        private class BBB {
            int a = 0;
        }

        BBB bbb = new BBB();

    }

    public static void main(String[] args) {

//        HashMap<String, String> aaa = (HashMap<String, String>) SerializableRW.read("d:\\desktop\\a.obj", null);

        Prefs prefs = new Prefs("d:\\desktop\\prefs");
        for (int i = 0; i < 1024 * 8; i++) {
            prefs.putString(i + "", i * i + "abcd");
        }

//        HashMap<String, String> aaa = new HashMap<>();
//        for (int i = 0; i < 1024 * 1024 * 8; i++) {
//            aaa.put(i + "", i + "");
//        }
//        SerializableRW.write("d:\\desktop\\a.obj", null, aaa);


//        Gson gson = new Gson();
//        String json = gson.toJson(new AAA());
//        AAA aaa = gson.fromJson(json, AAA.class);
//        System.out.println();

//        Object a = 123;
//        int i= (int) a;

//        MemCache.put("key", 123);
//        int value = (int) MemCache.get("key", 11);

//        System.out.println(value);

//        String[] aa = new String[0];
//        Object object = aa;

//        Object o = 1;
//
//        System.out.println(o.getClass().getSimpleName());

//        Prefs prefs = new Prefs("d:\\desktop\\a.obj", "aaaaaaaaaaaaaaaaaaaaaaaa");

//        boolean[] booleans = new boolean[]{true, false};

//        prefs.putBooleanArray("123", new boolean[]{true, false});
//        boolean[] booleans = prefs.getBooleanArray("123", null);
//        System.out.println(booleans);
//
//        prefs.putBoolean("123", true);
//        boolean b =prefs.getBoolean("123", false);
//        prefs.putInt("123", 123);
//        int a = prefs.getInt("123", 0);
//        System.out.println(a);

//        System.out.println(null instanceof Object);
//
//        System.out.println(StringHelper.nullOrBlank("   \t\n\r\b\f\n   "));


//        long start = System.currentTimeMillis();
//        Prefs prefs = new Prefs("d:\\desktop\\a.obj", "aaaaaaaaaaaaaaaaaaaaaaaa");
//        for (int i = 0; i < 1024; i++) {
//            prefs.putInt(i + "", i);
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);

//        prefs.putObject("123", null);
//        prefs.putObject("123", new Son());
//        prefs.putString("1213", "dohgoigrehtrh");
//        Father father = prefs.getObject("123", null, Son.class);

//        System.out.println("AAA");

//        long readFinish = System.currentTimeMillis();
//        System.out.println("readFinish=" + (readFinish - start));
//        for (int i = 0; i < 1024; i++) {
//            prefs.putObject(i + "", i + "aaaaaaaaaaaaaaaaaaaaaaaa");
//        }
//
//        long writeFinish = System.currentTimeMillis();
//        System.out.println("putFinish=" + (writeFinish - readFinish));

//        HashMap<String, String> map = new HashMap<>();
//
//        for (int i = 0; i < 4096; i++) {
//            map.put(i + "", i + "aaaaaaaaaaaaaaaaaaaaaaaa");
//        }
//
//        long start = System.currentTimeMillis();
//
//        SerializableRW.write("d:\\desktop\\a.obj", "aaaaaaaaaaaaaaaaaaaaaaaa", map);
//        long writeFinish = System.currentTimeMillis();
//        map = null;
//        map = SerializableRW.read("d:\\desktop\\a.obj", "aaaaaaaaaaaaaaaaaaaaaaaa");
//        long readFinish = System.currentTimeMillis();
//
//        System.out.println("write=" + (writeFinish - start));
//        System.out.println("read=" + (readFinish - writeFinish));

//        for (int i = 128; i <= Character.MAX_VALUE; i++) {
//            System.out.println((char)i);
//        }
//        ObjectStoreStatic.initialize("d:\\desktop\\objs", true, "aaaaaaaaaaaaaaaaaaaaaaaa");
//        String name = "test";
//        String object = "测试的字符串";
//        System.out.println(ObjectStoreStatic.write(name, object));
//        System.out.println(ObjectStoreStatic.read(name));
    }

}
