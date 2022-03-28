package io.muzoo.ssc.project.backend.timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TimestampDTO {

    private boolean success = false;

    private float timestamp;
}
