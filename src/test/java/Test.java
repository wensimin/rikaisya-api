import com.github.wensimin.rikaisya.api.RikaiType;
import com.github.wensimin.rikaisya.api.RikaiUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Test {

    /**
     * bv av 号测试
     */
    @org.junit.Test
    public void bilibiliTest() {
        String text = "爱走神BV1PA411v7Qu这是一个bv号所以BV1PA411v8Qu这也是bv,但是有没有av号呢12啊av456468131";
        Map<RikaiType, List<String>> rikaiRes = RikaiUtils.rikai(text);
        List<String> res = rikaiRes.get(RikaiType.bilibili);
        assert res.size() == 3;
        assert res.get(0).equals("BV1PA411v7Qu");
        assert res.get(1).equals("BV1PA411v8Qu");
        assert res.get(2).equals("av456468131");
    }

    /**
     * url 测试
     */
    @org.junit.Test
    public void urlTest() {
        String text = "这是https://regexr.com/网页\n" +
                "这也是http://regexr.com:44321\n" +
                "shali.tech.azsw/\n" +
                "ss://eGNoYWNoYTIwLWlldGYtcG8seTEzMDU6NzUzOTUxYW5uYS@192.168.0.200:44444/?plugin=C%3a%5cUsers%5cwensimin%5cDownloads%5cobfs-local%5cobfs-local.exe%3bobfs%3dhttp%3bobfs-host%3dbing.com#haproxy\n" +
                "www.sszzs.com:41511/\n" +
                "sozios.com\n" +
                "ftp://aaszs.c1\n" +
                "https://192.1azs.\n" +
                "......azswsx\n" +
                "chrome://version/";
        String[] urls = {
                "https://regexr.com/",
                "http://regexr.com:44321",
                "ss://eGNoYWNoYTIwLWlldGYtcG8seTEzMDU6NzUzOTUxYW5uYS@192.168.0.200:44444/?plugin=C%3a%5cUsers%5cwensimin%5cDownloads%5cobfs-local%5cobfs-local.exe%3bobfs%3dhttp%3bobfs-host%3dbing.com#haproxy",
                "ftp://aaszs.c1",
                "https://192.1azs."
        };
        Map<RikaiType, List<String>> rikaiRes = RikaiUtils.rikai(text);
        List<String> res = rikaiRes.get(RikaiType.url);
        assert res.size() == urls.length;
        Arrays.asList(urls).forEach(s -> {
            assert res.contains(s);
        });
    }

    /**
     * ip test
     */
    @org.junit.Test
    public void ipTest() {
        String text = "192.168.12.2\n" +
                "zheshiip192.168.0.1nizhidaoma\n" +
                "256.182.0.12chaoguo 255depipei";
        String[] ips = {"192.168.12.2", "192.168.0.1", "56.182.0.12"};
        Map<RikaiType, List<String>> rikaiRes = RikaiUtils.rikai(text);
        List<String> res = rikaiRes.get(RikaiType.ip);
        assert res.size() == ips.length;
        Arrays.asList(ips).forEach(s -> {
            assert res.contains(s);
        });
    }

    /**
     * ip test
     */
    @org.junit.Test
    public void codeTest() {
        String text = "1214512584yanzhenma\n" +
                "nideyanzhenmashi4431qingjieshou\n";
        String[] codes = {"12145125", "4431"};
        Map<RikaiType, List<String>> rikaiRes = RikaiUtils.rikai(text);
        List<String> res = rikaiRes.get(RikaiType.code);
        assert res.size() == codes.length;
        Arrays.asList(codes).forEach(s -> {
            assert res.contains(s);
        });
    }
}
