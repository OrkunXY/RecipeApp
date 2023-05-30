package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    ERROR_PASSWORD(2004,"Girmiş olduğunuz şifreler uyuşmamaktadır", HttpStatus.BAD_REQUEST),
    ERROR_USERNAME(2005,"Bu kullanıcı adı daha önce kayıt edilmiştir. Lütfen farklı bir kullanıcı adı giriniz.", HttpStatus.BAD_REQUEST),
    ERROR_NOT_FOUND_USERNAME(2006,"Böyle bir kullanıcı bulunmamaktadır.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000,"Geçersiz istek ya da parametre", HttpStatus.BAD_REQUEST),
    ERROR_INVALID_TOKEN(3000,"Geçersiz token bilgisi", HttpStatus.UNAUTHORIZED),
    ERROR_ACCESS_DENIED(3001,"Yetkisiz Erişim. Lütfen geçerli bir kullanıcı ile tekrar deneyin.", HttpStatus.UNAUTHORIZED),
    ERROR(9000, "Beklenmeyen bir hata oluştur. Lütfen tekrar deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR) ,
    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_DUPLICATE(4300, "Bu kullanıcı zaten kayıtlı", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_CREATED(4100, "Kullanıcı oluşturulamadı", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4400, "Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    INVALID_TOKEN(4600,"Token hatası" ,  HttpStatus.BAD_REQUEST),
    FOLLOW_ALREADY_EXIST(4700, "Böyle bir takip isteği daha önce oluşturulmuştur.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOLLOW(4800, "Kullanıcı kendisini takip edemez.", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(4900, "Girdiğiniz şifre ile eski şifreniz uyuşmamaktadır.", HttpStatus.BAD_REQUEST);


    int code;
    String message;
    HttpStatus httpStatus;

}
