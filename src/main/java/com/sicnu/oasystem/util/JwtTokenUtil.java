package com.sicnu.oasystem.util;

import com.sicnu.oasystem.pojo.Employee;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName JwtTokenUtil
 * @Description jwt-token的工具类，用于签发token，验证token等
 * @Author JohnTang
 * @LastChangeDate 2020/12/15
 * @Version v2.0
 */

@Component
public class JwtTokenUtil implements Serializable {

    // 密钥
    @Value("${jwt.secret}")
    private String secret;

    // 过期时间
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @MethodName getUsername
     * @param token
     * @Description 从token中获取用户名
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/15
     */
    public String getUsername(String token){
        return parseToken(token).getSubject();
    }

    /**
     * @MethodName calculateExpirationDate
     * @param createdDate
     * @Description 计算过期时间
     * @Author JohnTang
     * @Return java.util.Date
     * @LastChangeDate 2020/12/15
     */
    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    /**
     * @MethodName generateToken
     * @param subject 一般是用户名
     * @Description 生成token
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/15
     */
    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @MethodName parseToken
     * @param token
     * @Description 解析token
     * @Author JohnTang
     * @Return io.jsonwebtoken.Claims
     * @LastChangeDate 2020/12/15
     */
    private Claims parseToken(String token) throws ExpiredJwtException, SignatureException {
        Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        return claims;
    }

    /**
     * @MethodName isTokenExpired
     * @param claims
     * @param passwordChangeDate 用户最后一次修改密码的时间
     * @Description token是否过期
     * @Author JohnTang
     * @Return java.lang.Boolean
     * @LastChangeDate 2020/12/15
     */
    private Boolean isTokenExpired(Claims claims, Date passwordChangeDate) {
        Date expirationDate = claims.getExpiration();
        Date createDate = new Date(expirationDate.getTime() - expiration * 1000);

        // 如果签发的token未过期并且token在最后一次密码之后签发
        return !(expirationDate.before(new Date()) && createDate.after(passwordChangeDate));
    }

    /**
     * @MethodName refreshToken
     * @param token
     * @Description 刷新toekn
     * @Author JohnTang
     * @Return java.lang.String
     * @LastChangeDate 2020/12/15
     */
    public String refreshToken(String token) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = parseToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @MethodName validateToken
     * @param token
     * @param userDetails
     * @Description 验证token根据自己的需求自定义，这里是token的格式和是否过期和是否是本人
     * @Author JohnTang
     * @Return int
     * @LastChangeDate 2020/12/15
     */
    public Boolean validateToken(String token, UserDetails userDetails) throws ExpiredJwtException, SignatureException {
        Claims claims = parseToken(token);
        String username = claims.getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(claims, ((Employee)userDetails).getPasswordChangeDate()));
    }

}
