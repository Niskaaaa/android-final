package model;

public class Data {

    private String title;   //标题
    private String content; //内容
    private String times;   //时间
    private int ids;        //编号
    private String type;//类型
    private String imp;//重要程度

    public Data(String ti,int id,String con ,String time,String type,String imp){
        this.ids=id;
        this.title=ti;
        this.content=con;
        this.times=time;
        this.type=type;
        this.imp=imp;
    }//创建时

    public Data(String ti,String con,String time,String type,String imp){
        this.title=ti;
        this.content=con;
        this.times=time;
        this.type=type;
        this.imp=imp;
    }//newnote新建日记，通过adapter在生成id

    public Data(int i,String ti,String time,String type,String imp){
        this.ids=i;
        this.title=ti;
        this.times=time;
        this.type=type;
        this.imp=imp;//主页展示列表
    }

    public Data(String ti,String con,String type,String imp){
        this.title=ti;
        this.content=con;
        this.type=type;
        this.imp=imp;
    }//修改

    public int getIds() {
        return ids;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTimes() {
        return times;
    }

    public String getType() {
        return type;
    }
    public String getImp() {
        return imp;
    }
}
