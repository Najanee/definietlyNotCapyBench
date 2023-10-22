import { useRef, useState } from "react"
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
    toggleSubscribe: (resource: Topic | Subtopic | Post, resourceType: string) => void;

}

export function TopicBar({ topics, isSubscribed, subscribedTopics, setSubscribedTopics, subscribedSubtopics, setSubscribedSubtopics, toggleSubscribe } : Props) {
    const [topicsAndSubtopics, setTopicsAndSubtopics] = useState<(Topic | Subtopic)[]>([])
    const searchForTopics = (input: string) : (Topic | Subtopic)[] => {
        const topicsAndSubtopics : (Topic | Subtopic)[] = [];
        topicsAndSubtopics.concat(topics.flatMap(topic => topic.subtopics)).concat(topics);

        return topicsAndSubtopics.filter(topic => topic?.name.toLowerCase().includes(input?.toLowerCase()));
    }

    return (
        <div>
            <Input
                type="text"
                placeholder="Search topics"
                contentBefore={<Search16Regular/>}
                size="medium"
                className="w-full mb-4"
                onChange={(e) => searchForTopics(e.currentTarget.value)}
            />
            { 
                topics.map(topic => 
                    <div className="flex flex-col items-start w-full">
                        {
                            <TopicRow
                                key={topic.id} 
                                topic={topic}
                                isSubscribed={isSubscribed}
                                subscribedSubtopics={subscribedSubtopics}
                                subscribedTopics={subscribedTopics}
                                setSubscribedSubtopics={setSubscribedSubtopics}
                                setSubscribedTopics={setSubscribedTopics}
                                toggleSubscribe={toggleSubscribe}
                            />
                        } 
                    </div>
                )
            }
        </div>
    )
}