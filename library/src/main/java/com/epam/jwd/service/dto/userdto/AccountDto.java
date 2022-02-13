package com.epam.jwd.service.dto.userdto;

import com.epam.jwd.service.dto.EntityDto;

import java.util.Objects;

public class AccountDto extends EntityDto<Integer> {
    private UserDto user;
    private String firstName;
    private String secondName;
    private String phone;
    private String subscriptionId;

    public AccountDto(Integer id) {
        super(id);
    }

    public AccountDto(Integer id, UserDto user, String firstName, String secondName, String phone,
                   String subscriptionId) {
        super(id);
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.subscriptionId = subscriptionId;
    }

    public AccountDto(UserDto user, String firstName, String secondName, String phone,
                      String subscriptionId) {
        this.user = user;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phone = phone;
        this.subscriptionId = subscriptionId;
    }


    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return Objects.equals(user, that.user) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(phone, that.phone) && Objects.equals(subscriptionId, that.subscriptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, firstName, secondName, phone, subscriptionId);
    }

    @Override
    public String toString() {
        return "AccountDto{" + "user=" + user + ", firstName='" + firstName + '\'' + ", " +
                "secondName='" + secondName + '\'' + ", phone='" + phone + '\'' + ", " +
                "subscriptionId=" + subscriptionId + "} " + super.toString();
    }

    public static class AccountDtoBuilder {
        private Integer id;
        private UserDto user;
        private String firstName;
        private String secondName;
        private String phone;
        private String subscriptionId;

        public AccountDtoBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AccountDtoBuilder withUser(UserDto user) {
            this.user = user;
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

        public AccountDtoBuilder withSubscriptionId(String subscriptionId) {
            this.subscriptionId = subscriptionId;
            return this;
        }

        public AccountDto build() {
            AccountDto accountDto = new AccountDto(this.id);
            accountDto.setUser(this.user);
            accountDto.setFirstName(this.firstName);
            accountDto.setSecondName(this.secondName);
            accountDto.setPhone(this.phone);
            accountDto.setSubscriptionId(this.subscriptionId);
            return accountDto;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AccountDtoBuilder that = (AccountDtoBuilder) o;
            return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(phone, that.phone) && Objects.equals(subscriptionId, that.subscriptionId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, user, firstName, secondName, phone, subscriptionId);
        }

        @Override
        public String toString() {
            return "AccountDtoBuilder{" + "id=" + id + ", user=" + user + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\'' + ", phone='" + phone + '\'' + ", subscriptionId=" + subscriptionId + '}';
        }
    }
}
