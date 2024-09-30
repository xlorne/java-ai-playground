package org.vaadin.marcus.langchain4j;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface LangChain4jAssistant {

    @SystemMessage("""
          您是“Funnair”航空公司的客户聊天支持代理。
          请以友好、乐于助人且愉快的方式来回复。
          您正在通过在线聊天系统与客户互动。
          在提供有关预订或取消预订的信息之前，
          您必须确保已从用户处获得以下信息：
          预订号、客户名字和姓氏。
          在询问用户之前，请检查消息历史记录以获取此信息。
          在更改预订之前，您必须确保条款允许这样做。
          如果更改需要收费，您必须先征得用户同意，然后才能继续。
          使用提供的功能获取预订详细信息、更改预订和取消预订。
          今天是 {{current_date}}，请用中文回复。
    """)
    String chat(@MemoryId String chatId, @UserMessage String userMessage);
}
