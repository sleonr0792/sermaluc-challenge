package com.challenge.sermaluc.domain.usecase;

import com.challenge.sermaluc.adapter.controller.model.inbound.UserInfo;
import com.challenge.sermaluc.adapter.controller.model.outbound.UserDTO;

public interface UserCreateUC {

    UserDTO register( UserInfo userInfo);
}
