import com.github.wensimin.rikaisya.api.Rikai;
import com.github.wensimin.rikaisya.api.RikaiType;
import com.github.wensimin.rikaisya.api.RikaiUtils;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Test {

    /**
     * bv av 号测试
     */
    @org.junit.Test
    public void bilibiliTest() {
        String text = "爱走神BV1PA411v7Qu这是一个bv号所以BV1PA411v8Qu这也是bv,但是有没有av号呢12啊av456468131";
        Set<Rikai> res = RikaiUtils.rikai(text);
        List<String> resList = res.stream().filter(r -> r.getType() == RikaiType.bilibili).map(Rikai::getText).collect(Collectors.toList());
        assert resList.size() == 3;
        assert resList.contains("BV1PA411v7Qu");
        assert resList.contains("BV1PA411v8Qu");
        assert resList.contains("av456468131");
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
        Set<Rikai> res = RikaiUtils.rikai(text);
        List<String> resList = res.stream().filter(r -> r.getType() == RikaiType.url).map(Rikai::getText).collect(Collectors.toList());
        assert resList.size() == urls.length;
        Arrays.asList(urls).forEach(s -> {
            assert resList.contains(s);
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
        Set<Rikai> res = RikaiUtils.rikai(text);
        List<String> resList = res.stream().filter(r -> r.getType() == RikaiType.ip).map(Rikai::getText).collect(Collectors.toList());
        assert resList.size() == ips.length;
        Arrays.asList(ips).forEach(s -> {
            assert resList.contains(s);
        });
    }

    /**
     * ip test
     */
    @org.junit.Test
    public void codeTest() {
        String text = "1214512584yanzhenma\n" +
                "nideyanzhenmashi4431qingjieshou\n" +
                "4431,4431,12145125";
        String[] codes = {"12145125", "4431"};
        Set<Rikai> res = RikaiUtils.rikai(text);
        List<String> resList = res.stream().filter(r -> r.getType() == RikaiType.code).map(Rikai::getText).collect(Collectors.toList());
        assert resList.size() == codes.length;
        Arrays.asList(codes).forEach(s -> {
            assert resList.contains(s);
        });
    }

    /**
     * base64 test
     */
    @org.junit.Test
    public void base64Test() {
        String badText = "ss://eGNoYWNoYTIwLWlldGYtcG9seTEzMDU6NzUzOTUxYW5uY@192.168.0.200:44444/?plugin=C%3a%5cUsers%5cwensimin%5cDownloads%5cobfs-local%5cobfs-local.exe%3bobfs%3dhttp%3bobfs-host%3dbing.com#haproxy";
        assert RikaiUtils.rikai(badText).stream().filter(r -> r.getType() == RikaiType.base64).count() == 0;
        String text = "eGNoYWNoYTIwLWlldGYtcG9seTEzMDU6NzUzOTUxYW5uYQ==";
        String rikaiText = "xchacha20-ietf-poly1305:753951anna";
        Set<Rikai> res = RikaiUtils.rikai(text);
        assert new String(Base64.getDecoder().decode(text)).equals(rikaiText);
        assert res.size() == 1;
        res.forEach(rikai -> {
            assert rikai.getType() == RikaiType.base64;
            assert rikai.getText().equals(text);
            assert rikai.getRikaiText().equals(rikaiText);
        });
    }

}
