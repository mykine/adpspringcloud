package ug.aduser.adapter.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserParam implements Serializable {

    private static final long serialVersionUID = -4708809605850546209L;

    private String name;

    private Integer age;

    private Integer gender;
}
