import { useContext } from "react";
import { TeamsFxContext } from "./Context";
import config from "./sample/lib/config";
import { Body1, Button, Card, CardFooter, CardHeader, Persona, Text, Image } from "@fluentui/react-components";
import { topics, posts } from "../temporarydata/TemporaryData";
import { getTopics } from "../services/WebClient";
import { AddCircle16Regular, AddSquare16Regular } from "@fluentui/react-icons";
import PostCard from "./Post";

const showFunction = Boolean(config.apiName);

export default function Tab() {
  const { themeString } = useContext(TeamsFxContext);
  const getTheme = () : string => themeString === "default" ? "light" : themeString === "dark" ? "dark" : "contrast";

  // TODO fetch topics instead of hard coded

  return (
    <div className={`${getTheme()} w-3/5 m-auto`}>
      { // Topic display to be moved to separate component
      /* <div className = "flex justify-around py-5"> */}
      {/* {
        topics.map(topic => 
        <ToggleButton
        appearance="primary"
        shape="rounded"
        size="large"
        >
          {topic.name}
        </ToggleButton>)
      } */}
      {/* </div> */}
      <div className="flex flex-col max-w-3xl items-center m-auto">
        {posts.map(post => <PostCard post={post}/>)}
      </div>

      <Button
        icon= {<AddSquare16Regular />}
        size="medium"
        appearance="primary"
        iconPosition="before"
        onClick={getTopics}
      >
        <Text className="text-base">Create post</Text>
      </Button>
    </div>
  );
}
