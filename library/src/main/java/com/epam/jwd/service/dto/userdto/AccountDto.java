package com.epam.jwd.service.dto.userdto;

import com.epam.jwd.service.dto.EntityDto;

public class AccountDto extends EntityDto<Integer> {
    private Integer userId;
    private String firstName;
    private String secondName;
    private String phone;
    private Integer subscriptionId;

    public AccountDto(Integer id) {
        super(id);
    }

    public AccountDto(Integer id, Integer userId, String firstName, String secondName, String phone,
                   Integer subscriptionId) {
        super(id);
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.subscriptionId = subscriptionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public static class AccountDtoBuilder {
        private Integer id;
        private Integer userId;
        private String firstName;
        private String secondName;
        private String phone;
        private Integer subscriptionId;

        public  AccountDtoBuilder() {
        }

        public AccountDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AccountDtoBuilder withUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public AccountDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AccountDtoBuilder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public AccountDtoBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public AccountDtoBuilder withSubscriptionId(Integer subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
        }

        public AccountDto build() {
            AccountDto accountDto = new AccountDto(this.id);
            accountDto.setUserId(this.userId);
            accountDto.setFirstName(this.firstName);
            accountDto.setSecondName(this.secondName);
            accountDto.setPhone(this.phone);
            accountDto.setSubscriptionId(this.subscriptionId);
            return accountDto;
        }
    }
}
