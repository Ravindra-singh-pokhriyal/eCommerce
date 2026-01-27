package com.ravi.e_commerce.util;

import com.ravi.e_commerce.model.ProductOrder;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class CommonUtil {

    @Autowired
    private JavaMailSender mailSender;

    //Method to send mail to users
    public Boolean sentMail(String url, String reciepentEmail)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("**************@gmail.com", "SpringVault");  //it containe the mail id from which you want to send the mails to the users
        helper.setTo(reciepentEmail);
        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>" + "<p><a href=\"" +url
                + "\">Change my password</a></p>";

        helper.setSubject("Password Reset");
        helper.setText(content, true);
        mailSender.send(message);
        return true;
    }

    public static String generateUrl(HttpServletRequest request) {

        String siteUrl = request.getRequestURL().toString();

        return siteUrl.replace(request.getServletPath(), "");
    }

    // Template for product order notification emails.
    // Use a fresh copy of this template for every email to avoid accidental mutation
    // of the original template (previous implementation used a mutable field).
    private static final String ORDER_MAIL_TEMPLATE =
            "<p>Hello [[name]]</p><p>Thank you order <b>[[orderStatus]]</b>.</p>"
                    + "<p><b>Product Details : </b></p>"
                    + "<p>Name : [[productName]]</p>"
                    + "<p>Category : [[category]]</p>"
                    + "<p>Quantity : [[quantity]]</p>"
                    + "<p>Price : [[price]]</p>"
                    + "<p>Payment Type : [[paymentType]]</p>";

    // Sends an email about an order. This method uses a local copy of the template
    // and performs replacements on that copy so subsequent emails are not affected.
    public Boolean sendMailForProductOrder(ProductOrder order, String status) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("**************@gmail.com", "SpringVault");  // sender address
        helper.setTo(order.getOrderAddress().getEmail());

        // Work with a fresh copy of the template for each email
        String msg = ORDER_MAIL_TEMPLATE;

        // Replace placeholders with order-specific values. Keep replacements local.
        msg = msg.replace("[[name]]", order.getOrderAddress().getFirstName() != null ? order.getOrderAddress().getFirstName() : "");
        msg = msg.replace("[[orderStatus]]", status != null ? status : "");
        msg = msg.replace("[[productName]]", order.getProduct() != null ? order.getProduct().getTitle() : "");
        // product.getCategory() returns a String (category name) in the current model
        msg = msg.replace("[[category]]", order.getProduct() != null ? order.getProduct().getCategory() : "");
        msg = msg.replace("[[quantity]]", order.getQuantity() != null ? order.getQuantity().toString() : "");
        msg = msg.replace("[[price]]", order.getPrice() != null ? order.getPrice().toString() : "");
        msg = msg.replace("[[paymentType]]", order.getPaymentType() != null ? order.getPaymentType() : "");

        helper.setSubject("Product Order Status");
        helper.setText(msg, true);
        mailSender.send(message);
        return true;
    }
}
