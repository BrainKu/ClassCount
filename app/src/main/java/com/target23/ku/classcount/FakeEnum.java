package com.target23.ku.classcount;

/**
 * Created by zhengxinwei@N3072 on 2016/8/19.
 */
public enum FakeEnum {
    FAKE1 {
        @Override void test() {

        }
    }, FAKE2 {
        @Override void test() {

        }
    }, FAKE3 {
        @Override void test() {

        }
    }, FAKE4 {
        @Override void test() {

        }
    };

    abstract void test();
}
