import { useContext, useState, useEffect } from "react";
import { TeamsFxContext } from "./Context";
import { getData, subscribeToResource, unsubscribeFromResource } from "../services/WebClient";
import { PostCard } from "./PostCard";
import { TopicBar } from "./TopicBar";
import { Topic } from "../domain/Topic";
import { Post } from "../domain/Post";
import { Subtopic } from "../domain/Subtopic";
import { PostCreator } from "./PostCreator";

export function Tab() {
  const { themeString } = useContext(TeamsFxContext);
  const getTheme = () : string => themeString === "default" ? "light" : themeString === "dark" ? "dark" : "contrast";
  const USER_ID = 2;

  const [topics, setTopics] = useState<Topic[]>([]);
  const [isPostCreatorDisplayed, setIsPostCreatorDisplayed] = useState<boolean>(false)
  const [updatedDate, setUpdatedDate] = useState<Date>(new Date(Date.now()))

  const [subscribedTopics, setSubscribedTopics] 
    : [Topic[], (value: Topic[]) => void] = useState<Topic[]>([]);

  const [subscribedPosts, setSubscribedPosts] 
    : [Post[], (value: Post[]) => void] = useState<Post[]>([]);

  const [subscribedSubtopics, setSubscribedSubtopics] 
    : [Subtopic[], (value: Subtopic[]) => void] = useState<Subtopic[]>([]);

  const isSubscribed = (resource: Topic | Subtopic | Post) : Boolean => {
    return resource.subscriberIds?.includes(USER_ID);
  }

  const toggleSubscribe = (resource: Topic | Subtopic | Post, resourceType: string) : void => {
    console.log(isSubscribed(resource));

    if (isSubscribed(resource)) {
      let resourcePath: string;
      switch (resourceType) {
        case "post": {
          resourcePath = "fromPost";
          break;
        }
        case "subtopic": {
          resourcePath = "fromSubtopic";
          break;
        }
        case "topic": {
          resourcePath = "fromTopic";
          break;
        }
        default: {
          resourcePath = "a nie wiem"
        }
      }
      unsubscribeFromResource(USER_ID, resourcePath, resource.id, resourceType) 
    } else {
      let resourcePath: string;
      switch (resourceType) {
        case "post": {
          resourcePath = "toPost";
          break;
        }
        case "subtopic": {
          resourcePath = "toSubtopic";
          break;
        }
        case "topic": {
          resourcePath = "toTopic";
          break;
        }
        default: {
          resourcePath = "a nie wiem też..."
        }
      }
      subscribeToResource(USER_ID, resourcePath, resource.id, resourceType);
    }
    setUpdatedDate(new Date(Date.now()))
  } 
    
  useEffect(() => {
    getData(USER_ID).then(data => {
      setTopics(data.topics);
      setSubscribedTopics(data.subscribedTopics)
      setSubscribedSubtopics(data.subscribedSubtopics)
      setSubscribedPosts(data.posts);
    })
  }, [updatedDate])

  return (
    <div className={`${getTheme()} p-4 relative h-screen`}>
      <div className="flex align-left w-3/5 pl-40">
        <div className="flex flex-col items-center px-10 pb-10 relative">
            {subscribedPosts.map(post => 
              <PostCard
                key={post.id}
                userId={USER_ID}
                post={post}
                isSubscribed={isSubscribed}
                subscribedPosts={subscribedPosts}
                setSubscribedPosts={setSubscribedPosts}
                toggleSubscribe={toggleSubscribe}
              />
            )}
          <div className="blur-2xl w-full h-48 fixed bottom-0 left-0 bg-gray-400/20">
          </div>

        </div>      
      </div>

      <div className="w-400 top-10 right-0 fixed pr-80 mr-40">
        <TopicBar
          topics={topics}
          isSubscribed={isSubscribed}
          subscribedSubtopics={subscribedSubtopics}
          subscribedTopics={subscribedTopics}
          setSubscribedSubtopics={setSubscribedSubtopics}
          setSubscribedTopics={setSubscribedTopics}
          toggleSubscribe={toggleSubscribe}
        />
      </div>

      <div className="w-400 bottom-10 right-40 fixed pr-80 mr-40">
            <PostCreator 
                   topics={topics} 
                   userId={USER_ID} 
                   isDisplayed={isPostCreatorDisplayed} 
                   setIsDisplayed={setIsPostCreatorDisplayed}
                   setUpdatedDate={setUpdatedDate}
            />
          </div>
    </div>
  );
}