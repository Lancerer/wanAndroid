package com.lancer.wanandroid.net;


import com.lancer.wanandroid.bean.Article;
import com.lancer.wanandroid.bean.BannerBean;
import com.lancer.wanandroid.bean.DataResponse;
import com.lancer.wanandroid.bean.KnowledgeBean;
import com.lancer.wanandroid.bean.NavigationBean;
import com.lancer.wanandroid.bean.ProjectListBean;
import com.lancer.wanandroid.bean.ProjectTabBean;
import com.lancer.wanandroid.bean.User;

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

    /**
     * 用户登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @POST("/user/login")
    @FormUrlEncoded
    Observable<User> toLogin(@Field("username") String username, @Field("password") String password);

    /**
     * 用户注册接口
     *
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("/user/register")
    @FormUrlEncoded
    Observable<User> toRegister(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


    /**
     * 我的收藏
     *
     * @param page
     * @return
     */
    @GET("/lg/collect/list/{page}/json")
    Observable<Article> getCollects(@Path("page") int page);


    /**
     *添加收藏
     */
    @POST("/lg/collect/{id}/json")
    Observable<DataResponse> addCollect(@Path("id") int id);

    /**
     * 取消收藏
     */

    @POST("/lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<DataResponse> removeCollect(@Path("id") int id, @Field("originId") int originId);
}
