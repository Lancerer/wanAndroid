package com.lancer.wanandroid.net;


import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.BannerBean;
import com.lancer.wanandroid.bean.KnowledgeBean;
import com.lancer.wanandroid.bean.NavigationBean;
import com.lancer.wanandroid.bean.ProjectListBean;
import com.lancer.wanandroid.bean.ProjectTabBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * author: Lancer
 * date：2018/10/30
 * des:
 * email:tyk790406977@126.com
 */
public interface ApiService {
    /**
     * 轮播图接口
     *
     * @return
     */
    @GET("/banner/json")
    Observable<BannerBean> getBanner();


    /**
     * 返回首页文章数据
     *
     * @param page
     * @return
     */
    @GET("/article/list/{page}/json")
    Observable<Article> getHomeArticles(@Path("page") int page);

    /**
     * 返回项目分类数据
     *
     * @return
     */
    @GET("/project/tree/json")
    Observable<ProjectTabBean> getProjectTab();

    /**
     * 获得项目列表数据
     *
     * @return
     */
    @GET("/project/list/{curpage}/json")
    Observable<ProjectListBean> getProjectList(@Path("curpage") int curpage, @Query("cid") int id);

    /**
     * 获得知识体系的数据
     *
     * @return
     */
    @GET("/tree/json")
    Observable<KnowledgeBean> getKnowledge();

    /**
     * 知识体系下的文章
     *
     * @param page
     * @param cid
     * @return
     */
    @GET("/article/list/{page}/json")
    Observable<Article> getKnowledgeArticle(@Path("page") int page, @Query("cid") int cid);

    /**
     * 导航页数据
     *
     * @return
     */
    @GET("/navi/json")
    Observable<NavigationBean> getNavigation();
   /* //注册
    @POST("/user/register")
    @FormUrlEncoded
    Observable<Use> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
*/


    /**
     * 搜索功能接口
     *
     * @param page
     * @param k
     * @return
     */
    @POST("/article/query/{page}/json")
    @FormUrlEncoded
    Observable<Article> getSearchArticles(@Path("page") int page, @Field("k") String k);


}
