package com.example.chen.gank.data.bean;

import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/4/14 7:47
 */
public class GankFilterResult {
    /**
     * error : false
     * results : [{"_id":"5cac623a9d21220322355ec1","createdAt":"2019-04-09T09:13:30.178Z","desc":"阿里开源新一代混合技术方案 FlutterBoost","publishedAt":"2019-04-09T09:14:09.156Z","source":"web","type":"Android","url":"https://github.com/alibaba/flutter_boost","used":true,"who":"潇湘剑雨"},{"_id":"5caab6489d2122031c18f56d","createdAt":"2019-04-08T02:47:36.321Z","desc":"支持上拉加载更多，下拉刷新，可以自定义头部和底部，使用一个原生recyclerView就可以搞定复杂界面。支持自由切换状态【加载中，加载成功，加载失败，没网络等状态】的控件，可以自定义状态视图View。拓展功能【支持长按拖拽，侧滑删除】，轻量级 。已经用于多个实际项目中，持续更新\u2026\u2026","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1g1w6n01bt6j308c0go74r"],"publishedAt":"2019-04-09T00:53:48.433Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCRefreshView","used":true,"who":"潇湘剑雨"},{"_id":"5ca2e4f79d21225def25413f","createdAt":"2019-04-02T04:28:39.469Z","desc":"Flutter自定义实现神奇的卡片切换效果","images":["https://ww1.sinaimg.cn/large/0073sXn7gy1g1w6mzbds1g307w0dcnpg"],"publishedAt":"2019-04-07T10:02:03.420Z","source":"web","type":"Android","url":"https://github.com/BakerJQ/Flutter-InfiniteCards","used":true,"who":"潇湘剑雨"},{"_id":"5c9d722d9d21225de6278ce3","createdAt":"2019-03-29T01:17:33.854Z","desc":"Flutter之AndroidStudio版FishRedux模版代码生成插件","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p98sa633g30s00ew7wh"],"publishedAt":"2019-04-02T03:47:40.100Z","source":"web","type":"Android","url":"https://github.com/BakerJQ/FishReduxTemplateForAS","used":true,"who":"lijinshanmx"},{"_id":"5c9de3f79d21225de91ac038","createdAt":"2019-03-29T09:23:03.100Z","desc":"开源免费的IM，功能和UI符合国内习惯，比XMPP具有更适合移动端的协议，可以作为IM组件代替环信、融云、网易云信等云通讯和XMPP","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p98vc7jdj30u01ko43z","https://ww1.sinaimg.cn/large/0073sXn7ly1g1p98vnn4kj30u01kotae","https://ww1.sinaimg.cn/large/0073sXn7ly1g1p98wo75wj30u01kogv0"],"publishedAt":"2019-04-02T03:47:26.884Z","source":"web","type":"Android","url":"https://github.com/wildfirechat/android-chat","used":true,"who":"lijinshanmx"},{"_id":"5bbb01af9d21226111b86f0d","createdAt":"2018-10-08T07:05:19.297Z","desc":"适用于Android的灵活，强大且轻量级的插件框架【爱奇艺】","publishedAt":"2019-03-26T09:36:34.302Z","source":"chrome","type":"Android","url":"https://github.com/iqiyi/Neptune","used":true,"who":"潇湘剑雨"},{"_id":"5c950d2a9d21225def254128","createdAt":"2019-03-22T16:28:26.812Z","desc":"深度解耦Android App中全局加载中、加载失败及空数据视图，助力解决组件化改造过程中的解耦长征。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p98ot7img309i0ertah","https://ww1.sinaimg.cn/large/0073sXn7ly1g1p98pefbgg309i0ergpf"],"publishedAt":"2019-03-23T01:30:13.99Z","source":"web","type":"Android","url":"https://github.com/luckybilly/Gloading","used":true,"who":"潇湘剑雨"},{"_id":"5c09ff7b9d2122308e7445d8","createdAt":"2019-03-19T05:47:06.598Z","desc":"瓦力多渠道打包的Python脚本测试工具，简单实用。 ","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p96ou09rj30yg0nnast"],"publishedAt":"2019-03-19T05:47:10.201Z","source":"web","type":"Android","url":"https://github.com/yangchong211/YCWalleHelper","used":true,"who":"潇湘剑雨"},{"_id":"5c8747cf9d2122032f6b5aaf","createdAt":"2019-03-12T05:46:55.816Z","desc":"Flutter日益恒行，一篇入门级BaseWidget项目架构值得您的关注~","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1g1p98lhhcyg308c0dwx6q"],"publishedAt":"2019-03-13T01:32:11.450Z","source":"web","type":"Android","url":"https://blog.csdn.net/iamdingruihaha/article/details/88319883","used":true,"who":"潇湘剑雨"},{"_id":"5bbb061e9d2122610ee409d8","createdAt":"2018-10-08T07:24:14.959Z","desc":"WMRouter是一款Android路由框架，基于组件化的设计思路，有功能灵活、使用简单的特点。","publishedAt":"2019-03-12T01:41:06.815Z","source":"chrome","type":"Android","url":"https://github.com/meituan/WMRouter","used":true,"who":"潇湘剑雨"}]
     */

    private boolean error;
    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Gank> getResults() {
        return results;
    }

    public void setResults(List<Gank> results) {
        this.results = results;
    }
}
