import { Topic } from "../domain/Topic";
import { Input } from "@fluentui/react-components";
import { TopicRow } from "./TopicRow";
import { Search16Regular } from "@fluentui/react-icons";
import { Post } from "../domain/Post";
import { Subtopic } from "../domain/Subtopic";

type Props = {
    topics: Topic[];
    isSubscribed: (resource: Topic | Subtopic | Post) => Boolean;
    subscribedTopics: Topic[];
    setSubscribedTopics: (value:Topic[]) => void;
    subscribedSubtopics: Subtopic[];
    setSubscribedSubtopics: (value: Subtopic[]) => void;
}

export function TopicBar({ topics, isSubscribed, subscribedTopics, setSubscribedTopics, subscribedSubtopics, setSubscribedSubtopics } : Props) {

    return (
        <div className="w-200">
            <Input 
                type="text"
                placeholder="Search topics"
                contentBefore={<Search16Regular/>}
                size="medium"
                className="w-full mb-4"
            />
            { 
                topics.map(topic => 
                    <div className="flex flex-col items-start w-full">
                        {
                            <TopicRow 
                                topic={topic}
                                isSubscribed={isSubscribed}
                                subscribedSubtopics={subscribedSubtopics}
                                subscribedTopics={subscribedTopics}
                                setSubscribedSubtopics={setSubscribedSubtopics}
                                setSubscribedTopics={setSubscribedTopics}
                            />
                        } 
                    </div>
                )
            }
        </div>
    )
}