use japanese_address_parser::Parser;
use jni::objects::{JClass, JString};
use jni::sys::jstring;
use jni::JNIEnv;
use std::thread;

#[no_mangle]
pub unsafe extern "system" fn Java_com_example_rustandroidsandbox_MainViewModel_parseAddress<'local>(
    mut env: JNIEnv<'local>,
    _class: JClass<'local>,
    input: JString,
) -> jstring {
    let input: String = env.get_string(&input).unwrap().into();
    let handle = thread::spawn(move || {
        let mut result: String = "".to_string();
        let rt = tokio::runtime::Builder::new_current_thread()
            .enable_all()
            .build()
            .unwrap();
        rt.block_on(async {
            let parser = Parser();
            let parse_result = parser.parse(&input).await;
            result = serde_json::to_string(&parse_result).unwrap();
        });
        result
    });
    let output: JString = env.new_string(handle.join().unwrap()).unwrap();
    output.into_raw()
}
