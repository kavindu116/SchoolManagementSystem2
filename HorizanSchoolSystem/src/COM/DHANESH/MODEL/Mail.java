package COM.DHANESH.MODEL;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {

    Session newsession = null;
    MimeMessage m = null;

    //Supplier details for html
    String grn = "123456";
    String fname = "Sadun";
    String lname = "Amarasinghe";
    String desc = "Payment for june 2023";
    String amt = "Rs.40000";

    String reciever_mail = "dhaneshradhakrishnan2@gmail.com";
    String emailtitle = "Horizon Goods Recieved Note";

    String emailBody = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
            + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
            + "<head>\n"
            + "<!--[if gte mso 9]>\n"
            + "<xml>\n"
            + "  <o:OfficeDocumentSettings>\n"
            + "    <o:AllowPNG/>\n"
            + "    <o:PixelsPerInch>96</o:PixelsPerInch>\n"
            + "  </o:OfficeDocumentSettings>\n"
            + "</xml>\n"
            + "<![endif]-->\n"
            + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
            + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "  <meta name=\"x-apple-disable-message-reformatting\">\n"
            + "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n"
            + "  <title></title>\n"
            + "  \n"
            + "    <style type=\"text/css\">\n"
            + "      \n"
            + "      @media only screen and (min-width: 520px) {\n"
            + "        .u-row {\n"
            + "          width: 500px !important;\n"
            + "        }\n"
            + "\n"
            + "        .u-row .u-col {\n"
            + "          vertical-align: top;\n"
            + "        }\n"
            + "\n"
            + "        \n"
            + "            .u-row .u-col-100 {\n"
            + "              width: 500px !important;\n"
            + "            }\n"
            + "          \n"
            + "      }\n"
            + "\n"
            + "      @media only screen and (max-width: 520px) {\n"
            + "        .u-row-container {\n"
            + "          max-width: 100% !important;\n"
            + "          padding-left: 0px !important;\n"
            + "          padding-right: 0px !important;\n"
            + "        }\n"
            + "\n"
            + "        .u-row {\n"
            + "          width: 100% !important;\n"
            + "        }\n"
            + "\n"
            + "        .u-row .u-col {\n"
            + "          display: block !important;\n"
            + "          width: 100% !important;\n"
            + "          min-width: 320px !important;\n"
            + "          max-width: 100% !important;\n"
            + "        }\n"
            + "\n"
            + "        .u-row .u-col > div {\n"
            + "          margin: 0 auto;\n"
            + "        }\n"
            + "\n"
            + "\n"
            + "}\n"
            + "    \n"
            + "body{margin:0;padding:0}table,td,tr{border-collapse:collapse;vertical-align:top}.ie-container table,.mso-container table{table-layout:fixed}*{line-height:inherit}a[x-apple-data-detectors=true]{color:inherit!important;text-decoration:none!important}\n"
            + "\n"
            + "\n"
            + "table, td { color: #000000; } </style>\n"
            + "  \n"
            + "  \n"
            + "\n"
            + "</head>\n"
            + "\n"
            + "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #F7F8F9;color: #000000\">\n"
            + "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n"
            + "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n"
            + "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #F7F8F9;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
            + "  <tbody>\n"
            + "  <tr style=\"vertical-align: top\">\n"
            + "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
            + "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #F7F8F9;\"><![endif]-->\n"
            + "    \n"
            + "  \n"
            + "  \n"
            + "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
            + "  <div class=\"u-row\" style=\"margin: 0 auto;min-width: 320px;max-width: 500px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n"
            + "    <div style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
            + "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:500px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n"
            + "      \n"
            + "<!--[if (mso)|(IE)]><td align=\"center\" width=\"500\" style=\"width: 500px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
            + "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 500px;display: table-cell;vertical-align: top;\">\n"
            + "  <div style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
            + "  <!--[if (!mso)&(!IE)]><!--><div style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
            + "  \n"
            + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
            + "  <tbody>\n"
            + "    <tr>\n"
            + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
            + "        \n"
            + "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
            + "    <tbody>\n"
            + "      <tr style=\"vertical-align: top\">\n"
            + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
            + "          <span>&#160;</span>\n"
            + "        </td>\n"
            + "      </tr>\n"
            + "    </tbody>\n"
            + "  </table>\n"
            + "\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "  </tbody>\n"
            + "</table>\n"
            + "\n"
            + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
            + "  <tbody>\n"
            + "    <tr>\n"
            + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
            + "        \n"
            + "  <div style=\"font-size: 14px; line-height: 140%; text-align: center; word-wrap: break-word;\">\n"
            + "<p style=\"line-height: 140%; margin: 0px;\"><strong>Payment Succesfull " + fname + "! </strong></p>\n"
            + "<p style=\"line-height: 140%; margin: 0px;\"><strong>Here's your Digital Invoice.</strong></p>\n"
            + "  </div>\n"
            + "\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "  </tbody>\n"
            + "</table>\n"
            + "\n"
            + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
            + "  <tbody>\n"
            + "    <tr>\n"
            + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
            + "        \n"
            + "  <div style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n"
            + "<p style=\"line-height: 140%; margin: 0px;\"><strong>Details</strong></p>\n"
            + "<p style=\"line-height: 140%; margin: 0px;\"></p>\n"
            + "<p style=\"line-height: 140%; margin: 0px;\">Invoice No: " + grn + "</p>\n"
            + "<p style=\"line-height: 140%; margin: 0px;\">Student Name: " + fname + " " + lname + "<br />Description: " + desc + "</p>\n"
            + "<p style=\"line-height: 140%; text-align: left; margin: 0px;\">Paid Amount: " + amt + "</p><br/>\n"
            + "<p style=\"line-height: 140%; margin: 0px;\"></p>\n"
            + "<p style=\"line-height: 140%; margin: 0px;\">Note: Please keep this receipt securely</p>\n"
            + "<p style=\"line-height: 140%; margin: 0px;\"></p>\n"
            + "  </div>\n"
            + "\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "  </tbody>\n"
            + "</table>\n"
            + "\n"
            + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
            + "  <tbody>\n"
            + "    <tr>\n"
            + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
            + "        \n"
            + "  <table height=\"0px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
            + "    <tbody>\n"
            + "      <tr style=\"vertical-align: top\">\n"
            + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n"
            + "          <span>&#160;</span>\n"
            + "        </td>\n"
            + "      </tr>\n"
            + "    </tbody>\n"
            + "  </table>\n"
            + "\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "  </tbody>\n"
            + "</table>\n"
            + "\n"
            + "<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
            + "  <tbody>\n"
            + "    <tr>\n"
            + "      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
            + "        \n"
            + "  <div style=\"font-size: 14px; line-height: 140%; text-align: center; word-wrap: break-word;\">\n"
            + "<p style=\"line-height: 140%; margin: 0px;\">Horizon International School (PVT) LTD</p></div>\n"
            + "\n"
            + "      </td>\n"
            + "    </tr>\n"
            + "  </tbody>\n"
            + "</table>\n"
            + "\n"
            + "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n"
            + "  </div>\n"
            + "</div>\n"
            + "<!--[if (mso)|(IE)]></td><![endif]-->\n"
            + "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
            + "    </div>\n"
            + "  </div>\n"
            + "  </div>\n"
            + "  \n"
            + "\n"
            + "\n"
            + "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
            + "    </td>\n"
            + "  </tr>\n"
            + "  </tbody>\n"
            + "  </table>\n"
            + "  <!--[if mso]></div><![endif]-->\n"
            + "  <!--[if IE]></div><![endif]-->\n"
            + "</body>\n"
            + "\n"
            + "</html>";

    public static void main(String[] args) {
        System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");

        try {
            Mail mail = new Mail();
            mail.setupServerProperties();
            mail.draftEmail("backup.sql");
            mail.sendEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEmail() throws MessagingException {
        // Email details
        String fromUser = "no-reply@horizon-school.online";
        String password = "1459e373-F9DF-4E54-a2a4-60E4d67c1349"; // password
        String emailHost = "mail.spacemail.com";

        // Ensure authentication is correctly performed
        Transport tp = newsession.getTransport("smtp");  // Use SMTP with SSL
        tp.connect(emailHost, fromUser, password);  // Connect with the credentials
        tp.sendMessage(m, m.getAllRecipients());
        tp.close();
        System.out.println("Email sent successfully");
    }

    private MimeMessage draftEmail(String filePath) throws AddressException, MessagingException, IOException {
        m = new MimeMessage(newsession);
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(reciever_mail));

        m.setSubject(emailtitle);
        m.setFrom(new InternetAddress("no-reply@horizon-school.online"));

        // Create a multipart to hold the email body and the attachment
        MimeMultipart mmp = new MimeMultipart();

        // Email body part
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");
        mmp.addBodyPart(bodyPart);

        // Attachment part
        MimeBodyPart attachmentPart = new MimeBodyPart();
        File file = new File(filePath);
        attachmentPart.attachFile(file); // Attach the file
        attachmentPart.setFileName(file.getName()); // Set the file name
        mmp.addBodyPart(attachmentPart);

        // Set the content of the message to the multipart
        m.setContent(mmp);

        return m;
    }

    private void setupServerProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", "mail.spacemail.com");
        p.put("mail.smtp.port", "465");  // Port for SSL
        p.put("mail.smtp.auth", "true");  // Enable SMTP authentication
        p.put("mail.smtp.ssl.enable", "true");  // Enable SSL encryption
        //p.put("mail.debug", "true");  // Enable debug logs

        // Create custom authenticator to handle credentials
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("no-reply@horizon-school.online", "1459e373-F9DF-4E54-a2a4-60E4d67c1349");  // Ensure the app password is used
            }
        };

        // Set the session with the authenticator
        newsession = Session.getInstance(p, authenticator);
    }
}
