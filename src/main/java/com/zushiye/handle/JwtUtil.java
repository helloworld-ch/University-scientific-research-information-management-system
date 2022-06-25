package com.zushiye.handle;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : zushiye
 * @create 2022/5/16 18:34
 */

public class JwtUtil {
    final static String base64EncodedSecretKey = "base64EncodedSecretKey";//私钥
    final static long TOKEN_EXP = 1000 * 60*60*24*7;//过期时间,7天

    public static String getToken(String userName,String role) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    /**
     * @Date:17-12-12 下午6:21
     * @Author:root
     * @Desc:检查token,只要不正确就会抛出异常
     **/
    public static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }

    /**
     * 根据jwt token 获取用户id
     * @param token
     * @return
     */
    public static String getUserId(String token) throws ServletException {
        return Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * 根据jwt token 获取用户角色
     * @param token
     * @return
     */
    public static String getRole(String token){
        return (String) Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody().get("roles");
    }
}
