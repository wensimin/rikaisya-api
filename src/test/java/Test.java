import com.github.wensimin.rikaisya.api.RikaiType;
import com.github.wensimin.rikaisya.api.RikaiUtils;

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
}
