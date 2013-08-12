package jobengine.app.ex;

import javax.ws.rs.core.Response.Status;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestHttpStatus {

    /**
     * The status code to use for the response.
     *
     * @see javax.servlet.http.HttpServletResponse#setStatus(int)
     */
    Status value();

    /**
     * The reason to be used for the response. <p>If this element is not set, it will default to the standard status
     * message for the status code. Note that due to the use of {@code HttpServletResponse.sendError(int, String)},
     * the response will be considered complete and should not be written to any further.
     *
     * @see javax.servlet.http.HttpServletResponse#sendError(int, String)
     */
    String reason() default "";

}
