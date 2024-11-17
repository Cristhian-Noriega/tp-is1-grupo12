package is1.order_app.dto;

import org.springframework.lang.NonNull;


public record TokenDTO(
        @NonNull String accessToken
) {
}

