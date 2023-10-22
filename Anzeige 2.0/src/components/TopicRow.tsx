import { useState } from "react"
import { ChevronDown16Regular, ChevronRight16Regular } from "@fluentui/react-icons";
import { Topic } from "../domain/Topic"
import { Button, Divider } from "@fluentui/react-components";
import { Subtopic } from "../domain/Subtopic";
import { Post } from "../domain/Post";

type Props = {
    topic: Topic;
    isSubscribed: (resource: Topic | Subtopic | Post) => Boolean;
    subscribedTopics: Topic[];
    setSubscribedTopics: (value:Topic[]) => void;
    subscribedSubtopics: Subtopic[];
    setSubscribedSubtopics: (value: Subtopic[]) => void;
    toggleSubscribe: (resource: Topic | Subtopic | Post, resourceType: string) => void;
}

export function TopicRow( { topic, isSubscribed, subscribedTopics, setSubscribedTopics, subscribedSubtopics, setSubscribedSubtopics, toggleSubscribe } : Props ) {
    const [isExpanded, setIsExpanded] = useState<Boolean>(false);

    const toggleIsExpanded = () => {
        setIsExpanded(prev => !prev)
    }

    return (
        <>
            <div className="flex items-center">
            <Button
                key={topic.id}
                icon={ isExpanded ? <ChevronDown16Regular/> : <ChevronRight16Regular />}
                appearance="transparent"
                onClick={toggleIsExpanded}
            >
                {topic.name}
            </Button>
            <Divider vertical />
            {
                 <Button
                    className="mt-1"
                    appearance="transparent"
                    size="small"
                    onClick={() => toggleSubscribe(topic, "topic")}
                >
                    { !isSubscribed(topic) ? "Subscribe" : "Unsubscribe" } 
                </Button>
            }
            </div>
            
            {
                isExpanded && topic.subtopics.map(subtopic => 
                    <p className="px-4 py-1.5 text-ellipsis text-left leading-5 w-full">
                        {subtopic.name}
                        <Divider vertical />
                        <Button
                            size="small"
                            appearance="transparent"
                            onClick={() => toggleSubscribe(subtopic, "subtopic")}
                        >
                            { !isSubscribed(subtopic) ? "Subscribe" : "Unsubscribe" }
                        </Button>
                    </p>
                )
            }
        </>
    )
}