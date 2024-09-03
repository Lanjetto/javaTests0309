package com.nexign.service

import com.nexign.model.User
import com.nexign.repository.UserRepository
import spock.lang.Specification

class UserServiceSpockTest extends Specification {

    def service = new UserService()

    def "логин провален при неверных значениях"(String login, String pass) {
        when:
        this.service.login(login, pass)

        then:
        thrown(IllegalArgumentException)

        where:
        login | pass
        "alex"| null
        null  | "345"
        null  | null

    }


    def "тест корректной авторизации"() {
        given:
        def user = new User(0L, "Alex", "123")
        def service = new UserService(Stub(UserRepository) {
            getUserList() >> List.of(user)
        })

        when:
        def list = service.getUserList()

        then:
        list == [user]
    }

}
