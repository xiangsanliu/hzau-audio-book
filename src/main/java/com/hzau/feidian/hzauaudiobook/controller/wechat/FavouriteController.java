package com.hzau.feidian.hzauaudiobook.controller.wechat;

import com.hzau.feidian.hzauaudiobook.service.wechat.FavouriteService;
import com.hzau.feidian.hzauaudiobook.share.ResponseBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 项三六
 * @time 2019/4/15 12:19
 * @comment 收藏
 */

@RestController
@RequestMapping("wechat/{openid}/favourite/")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @RequestMapping("getByUser")
    public ResponseBean getByUser(@PathVariable String openid) {
        return ResponseBean.ok(null, favouriteService.getFavourite(openid));
    }

    @RequestMapping("addBook/{id}")
    public ResponseBean addBook(@PathVariable String openid, @PathVariable long id) {
        favouriteService.addFavourite(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("addShort/{id}")
    public ResponseBean addShort(@PathVariable String openid, @PathVariable long id) {
        favouriteService.addFavourite(openid, id, false);
        return ResponseBean.ok();
    }

    @RequestMapping("removeBook/{id}")
    public ResponseBean removeBook(@PathVariable String openid, @PathVariable long id) {
        favouriteService.removeFavourite(openid, id, true);
        return ResponseBean.ok();
    }

    @RequestMapping("removeShort/{id}")
    public ResponseBean removeShort(@PathVariable String openid, @PathVariable long id) {
        favouriteService.removeFavourite(openid, id, false);
        return ResponseBean.ok();
    }

}
