package com.hero.controller;

import com.hero.enums.ResultEnum;
import com.hero.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.net.URLEncoder;

/**
 * @program: springboot
 * @description: 微信登录验证获取信息的Contrller
 * @author: LiNing
 * @create: 2019-04-11 12:25
 **/
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatContrller {

    @Autowired
    private WxMpService wxMpService;

    @Resource
    private StringRedisTemplate redisTemplate0;

    @RequestMapping(value = "/qrAuthorize",method = RequestMethod.GET)
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        //可以改用集中管理
        String url = "";
        String redirectUrl = wxMpService.buildQrConnectUrl(url, WxConsts.QRCONNECT_SCOPE_SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/qrUserInfo",method = RequestMethod.GET)
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[qrUserInfo]{}", e);
            throw new MyException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}