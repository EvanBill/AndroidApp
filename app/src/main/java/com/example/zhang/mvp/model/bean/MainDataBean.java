package com.example.zhang.mvp.model.bean;

import java.util.List;

public class MainDataBean {

    /**
     * date : 20180827
     * stories : [{"images":["https://pic4.zhimg.com/v2-4acae01608d8364612a87e31cccd38bb.jpg"],"type":0,"id":9694273,"ga_prefix":"082716","title":"医疗资源当然不能「按闹分配」「按惨分配」，那应该谁先来？"},{"images":["https://pic3.zhimg.com/v2-4a1b51292175830d88d1befba2b3dee2.jpg"],"type":0,"id":9694240,"ga_prefix":"082716","title":"我们没有输给对手，我们只是活生生输给了自己"},{"images":["https://pic1.zhimg.com/v2-839397bd15caf1c10fce33248af00e18.jpg"],"type":0,"id":9694264,"ga_prefix":"082715","title":"夏天夏天悄悄过去，留下\u2026\u2026的防晒霜还需要天天用吗？"},{"images":["https://pic4.zhimg.com/v2-355076f5f5d1f2106a71a27c5548f55b.jpg"],"type":0,"id":9694237,"ga_prefix":"082712","title":"大误 · 一切等到哈利 · 波特考完试再说"},{"images":["https://pic4.zhimg.com/v2-976dc9d6d2219262d680eaf98feae717.jpg"],"type":0,"id":9694244,"ga_prefix":"082711","title":"- 我是滴滴司机，刚被抓了要罚款\r\n- 没事我报销，继续开你的"},{"images":["https://pic1.zhimg.com/v2-c19aef18f7240da4729f2718f40d3d58.jpg"],"type":0,"id":9693532,"ga_prefix":"082710","title":"我现在要画三个圈圈，给你讲解下高铁驾驶室的奥秘"},{"images":["https://pic1.zhimg.com/v2-b2588e8c237d714f2297cef3f7597860.jpg"],"type":0,"id":9693620,"ga_prefix":"082709","title":"跑个马拉松，中途停下来吃几口烤全羊，美滋滋"},{"images":["https://pic1.zhimg.com/v2-17dcbcc27adf77eed941fd8704d3a618.jpg"],"type":0,"id":9693997,"ga_prefix":"082708","title":"如果你不是大猩猩的话，那虹鳟鱼也不是三文鱼"},{"title":"我从北五环垃圾站，淘到一部中国家庭野史","ga_prefix":"082707","images":["https://pic3.zhimg.com/v2-50f6babbdc8bd86c895e9f4835f926a6.jpg"],"multipic":true,"type":0,"id":9694214},{"images":["https://pic3.zhimg.com/v2-71a4c9b435b84f3df3c26820ff66ceca.jpg"],"type":0,"id":9694099,"ga_prefix":"082707","title":"中国队在 2002 世界杯为什么会出线？"},{"images":["https://pic4.zhimg.com/v2-234156382ad145c352936af1d12b34eb.jpg"],"type":0,"id":9694232,"ga_prefix":"082706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-d1dc26b7625a37513443a4eb54869a11.jpg","type":0,"id":9694244,"ga_prefix":"082711","title":"- 我是滴滴司机，刚被抓了要罚款\r\n- 没事我报销，继续开你的"},{"image":"https://pic3.zhimg.com/v2-ba3d5ba1fe2b294a12c084b2f87d4cc6.jpg","type":0,"id":9694214,"ga_prefix":"082707","title":"我从北五环垃圾站，淘到一部中国家庭野史"},{"image":"https://pic1.zhimg.com/v2-72e9b33ae0f92eeb9b0bcc2d2dd94e7c.jpg","type":0,"id":9694158,"ga_prefix":"082519","title":"又是滴滴顺风车，又有女性被害，为什么失误一再出现？"},{"image":"https://pic1.zhimg.com/v2-46882cf53632a020c3abc14fc8fa8118.jpg","type":0,"id":9694034,"ga_prefix":"082521","title":"今晚来聊 · 温情脉脉合家欢的《蚁人 2》，彩蛋一出心凉了半截"},{"image":"https://pic1.zhimg.com/v2-59b8e5999586ce545aca38a7c6f79d00.jpg","type":0,"id":9694100,"ga_prefix":"082618","title":"北京不靠大江大河，为什么出「北京烤鸭」这样的美食？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    @Override
    public String toString() {
        return "MainDataBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic4.zhimg.com/v2-4acae01608d8364612a87e31cccd38bb.jpg"]
         * type : 0
         * id : 9694273
         * ga_prefix : 082716
         * title : 医疗资源当然不能「按闹分配」「按惨分配」，那应该谁先来？
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", multipic=" + multipic +
                    ", images=" + images +
                    '}';
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-d1dc26b7625a37513443a4eb54869a11.jpg
         * type : 0
         * id : 9694244
         * ga_prefix : 082711
         * title : - 我是滴滴司机，刚被抓了要罚款
         - 没事我报销，继续开你的
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
