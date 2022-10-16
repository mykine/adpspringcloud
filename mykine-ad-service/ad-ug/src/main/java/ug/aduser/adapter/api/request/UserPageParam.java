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
public class UserPageParam implements Serializable {

    private static final long serialVersionUID = 3792508997738268773L;

    private Integer page;

    private Integer size;
}
