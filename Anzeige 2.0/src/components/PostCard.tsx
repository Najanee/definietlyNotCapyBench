import {Card, CardFooter, CardHeader, Persona, Image, Text, Divider, Button, Tag } from "@fluentui/react-components";
import { Post } from "../domain/Post";
import { Comment16Regular, Share16Regular, Star16Filled, StarAdd16Regular } from "@fluentui/react-icons";
import { Topic } from "../domain/Topic";
import { Subtopic } from "../domain/Subtopic";

type Props = {
    userId: number;
    post: Post
    isSubscribed: (resource: Topic | Subtopic | Post) => Boolean;
    subscribedPosts: Post[];
    setSubscribedPosts: (value: Post[]) => void;
    toggleSubscribe: (resource: Topic | Subtopic | Post, resourceType: string) => void;
}

export function PostCard( { post, isSubscribed, toggleSubscribe }: Props) {

    return (
    <Card
        as="div"
        size="large"
        className="mb-10 px-8 w-full py-8"
        appearance="filled"
    >
      <CardHeader
        header={
          <div className="flex justify-between content-center w-full p-2" >
              <Persona
                 avatar={{
                  image: {
                    src: post.author.imageUrl
                  },
                }}
                size="huge"
                name = {post.author.name}
                secondaryText= {post.createDate.toLocaleTimeString()}
                textAlignment="center"/>
              <Tag>
                <Text 
                  weight="semibold"
                >
                  {post.subtopic ? `${post.topic.name} #${post.subtopic.name}` : post.topic.name}
                </Text>
              </Tag>
          </div>
        }
      />
      <div>
        <h2 className="text-base mb-1 font-medium">{post.title}</h2>
        <p>{post.content}</p>
      </div>
      <Divider />
      <CardFooter
        className="flex justify-between content-center"
      >
        <Text className="justify-self-start">Observed by {post.subscriberIds.length} people.</Text>
        <div className="justify-self-end flex content-center justify-between w-1/5">
          <Button 
            icon={isSubscribed(post) ? <Star16Filled /> : <StarAdd16Regular />}
            onClick={() => toggleSubscribe(post, "post")}
          />
          <Button 
            icon={<Share16Regular />} 
          />
          <Button 
            icon={<Comment16Regular />} 
          />
        </div>
      </CardFooter>
    </Card>
    )
}