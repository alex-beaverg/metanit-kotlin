package chapter04.packs.email

class Message(val text: String)

fun send(message: Message, address: String) {
    println("Сообщение `${message.text}` было отправлено по адресу: $address")
}