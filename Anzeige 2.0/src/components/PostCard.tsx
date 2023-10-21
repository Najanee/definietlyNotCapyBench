import {Card, CardFooter, CardHeader, Persona, Image, Text, Divider, Button } from "@fluentui/react-components";
import { Post } from "../domain/Post";
import { Chat16Regular, Comment16Regular, Share16Regular, StarAdd16Regular } from "@fluentui/react-icons";

type Props = {
    post: Post
}

export function PostCard( { post }: Props) {
    return (
    <Card
        as="div"
        size="large"
        className="mb-10 p-0"
        appearance="filled"
    >
      <CardHeader
        header={
          <div className="flex justify-between content-center w-full" >
              <Persona 
                avatar= {
                  <Image
                  shape="circular"
                  fit="cover"
                  src={post.author.avatar}
                  alt={`${post.author.name} avatar`}
                  />
                }
                name = {post.author.name}
                secondaryText= {post.createdDate.toLocaleTimeString()}
                textAlignment="center"
              />
              <div className="flex content-center">
                <Chat16Regular 
                  className="mx-0.5"
                />
                <Text 
                  weight="semibold"
                >
                  {post.subtopic ? `${post.topic.name} #${post.subtopic.name}` : post.topic.name}
                </Text>
              </div>
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
        <Text className="justify-self-start">Observed by x people.</Text>
        <div className="justify-self-end flex content-center justify-between w-1/5">
          <Button icon = {<Share16Regular />} />
          <Button icon = {<StarAdd16Regular />} />
          <Button icon = {<Comment16Regular />} />
        </div>
      </CardFooter>
    </Card>
    )
}