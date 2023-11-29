use jni::JNIEnv;
use jni::objects::{JClass, JString};
use jni::sys::jstring;

pub fn say_hello(name: String) -> String {
    format!("Hello, {}", name)
}

#[no_mangle]
pub unsafe extern "system" fn Java_com_example_rustandroidsandbox_MainViewModel_sayHello<'local>(
    mut env: JNIEnv<'local>,
    _class: JClass<'local>,
    input: JString<'local>,
) -> jstring {
    let input: String = env.get_string(&input).unwrap().into();
    let output: JString = env.new_string(
        say_hello(input)
    ).unwrap();
    output.into_raw()
}