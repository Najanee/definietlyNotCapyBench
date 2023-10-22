import { useState } from "react"
import { ChevronDown16Regular, ChevronRight16Regular, Star16Regular, StarAdd16Regular } from "@fluentui/react-icons";
import { Topic } from "../domain/Topic"
import { Button, Tag } from "@fluentui/react-components";
import { Subtopic } from "../domain/Subtopic";
import { Post } from "../domain/Post";

type Props = {
    topic: Topic;
    isSubscribed: (resource: Topic | Subtopic | Post) => Boolean;
    subscribedTopics: Topic[];
    setSubscribedTopics: (value:Topic[]) => void;
    subscribedSubtopics: Subtopic[];
    setSubscribedSubtopics: (value: Subtopic[]) => void;

}

export function TopicRow( { topic, isSubscribed, subscribedTopics, setSubscribedTopics, subscribedSubtopics, setSubscribedSubtopics } : Props ) {
    const [isExpanded, setIsExpanded] = useState<Boolean>(false);

    const toggleSubscribeTopic = (handledTopic: Topic) : void => {
        if(isSubscribed(handledTopic)) {
          const dimnishedTopics = [...subscribedTopics];
          dimnishedTopics.splice(subscribedTopics.findIndex(topic => topic.id === handledTopic.id), 1)
          setSubscribedTopics(dimnishedTopics);
        } else {
          setSubscribedTopics([...subscribedTopics, handledTopic]);
        }
      }

      const toggleSubscribeSubtopic = (handledSubtopic: Subtopic) : void => {
        if(isSubscribed(handledSubtopic)) {
          const dimnishedSubtopics = [...subscribedSubtopics];
          dimnishedSubtopics.splice(subscribedSubtopics.findIndex(subtopic => subtopic.id === handledSubtopic.id), 1)
          setSubscribedSubtopics(dimnishedSubtopics);
        } else {
            setSubscribedSubtopics([...subscribedSubtopics, handledSubtopic]);
        }
      }

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
                {topic.name} |
            </Button>
            {
                 <Button
                    className="mt-1"
                    appearance="transparent"
                    size="small"
                    onClick={() => toggleSubscribeTopic(topic)}
                >
                    { isSubscribed(topic) ? "Subscribe" : "Unsubscribe" } 
                </Button>
            }
            </div>
            
            {
                isExpanded && topic.subtopics.map(subtopic => 
                    <p className="px-4 py-1.5 text-ellipsis leading-5 w-full">
                        {subtopic.name} | 
                        {
                            <Button
                                size="small"
                                appearance="transparent"
                                onClick={() => toggleSubscribeSubtopic(subtopic)}
                            >
                                { isSubscribed(subtopic) ? "Subscribe" : "Unsubscribe" }
                            </Button>
                        }
                    </p>
                )
            }
        </>
        
    )
}