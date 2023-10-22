import { useState } from "react";
import { Button, Combobox, Dialog, DialogActions, DialogBody, DialogContent, DialogSurface, DialogTitle, DialogTrigger, Input, Label, Menu, MenuItem, MenuList, MenuPopover, MenuTrigger, Option, Skeleton, SkeletonItem, Textarea, Toolbar, ToolbarButton, ToolbarDivider } from "@fluentui/react-components";
import { Topic } from "../domain/Topic";
import { AddSquareMultiple16Regular, CommentEdit24Regular, FontDecrease24Regular, FontIncrease24Regular, MoreHorizontal24Filled, TextFont24Regular } from "@fluentui/react-icons";
import { Post } from "../domain/Post";
import { NewPost } from "./dto/NewPost";
import { getData, postNewPost } from "../services/WebClient";

type Props = {
    topics: Topic[];
    userId: number;
    isDisplayed: boolean;
    setIsDisplayed: (value: boolean) => void;
    
}

export function PostCreator({ topics, userId, setIsDisplayed, isDisplayed } : Props) {
    const [selectedTopicName, setSelectedTopicName] = useState<string>("");
    const [selectedSubtopicName, setSelectedSubtopicName] = useState<string>("");
    const [title, setTitle] = useState<string>("");
    const [content, setContent] = useState<string>("");
    const inputId = "title";

    const doSubtopicsExistForTopic = () => {
        return !(topics.find(topic => topic.name === selectedTopicName)?.subtopics.length === 0);
    }

    const createPost = () => {
        const selectedTopic = topics.find(topic => topic.name === selectedTopicName);
        const selectedSubtopic = selectedTopic!.subtopics.find(subtopic => subtopic.name === selectedSubtopicName);

        postNewPost(new NewPost(title, content, userId, selectedTopic!.id, selectedSubtopic?.id))
            .then(response => getData(userId));
    }

    return (
        <Dialog open={isDisplayed} modalType="non-modal" inertTrapFocus={true}>
            <DialogTrigger action="open">
                <Button
                    icon= {<AddSquareMultiple16Regular />}
                    size="medium"
                    appearance="primary"
                    iconPosition="before"
                    onClick={() => setIsDisplayed(true)}
                    >
                    New post
                </Button>
            </DialogTrigger>
            <DialogSurface 
                backdrop={
                    <div className="blur-sm transparent"></div>
                }
            >
                <DialogBody>
                    <DialogTitle className="py-3 flex items-center">
                        <CommentEdit24Regular className="mr-2"/>
                        <h1 className="text-2xl">New Post</h1>
                    </DialogTitle>
                    <DialogContent>
                            <div className="flex justify-between">
                                <div className="flex flex-col">
                                    <Label htmlFor={inputId}>Select topic</Label>
                                    <Combobox
                                        multiselect={false}
                                        placeholder="Topic"
                                        onOptionSelect={(event, data) => setSelectedTopicName(data.selectedOptions[0])}
                                    >
                                        {topics.map(topic => 
                                            <Option 
                                                key={topic.id} 
                                            >
                                                {topic.name}
                                            </Option>
                                        )}
                                    </Combobox>
                                </div>
                                <div className="flex flex-col pb-5">
                                    <label id="1">Select subtopic</label>
                                    <Combobox
                                        disabled = {!doSubtopicsExistForTopic()}
                                        aria-labelledby="1"
                                        placeholder={doSubtopicsExistForTopic() ? "Subtopic" : "No subtopics for topic"}
                                        onOptionSelect={(event, data) => setSelectedSubtopicName(data.selectedOptions[0])}
                                    >
                                        {topics.find(topic => topic.name === selectedTopicName)?.subtopics.map(subtopic => 
                                            <Option key={subtopic.id}>
                                                {subtopic.name}
                                            </Option>
                                        )}
                                    </Combobox>
                                </div>
                            </div>
                            <div className="flex flex-col pb-5">
                                <Label htmlFor={inputId}>Title</Label>
                                <Input
                                    id={inputId}
                                    onChange={(event, data) => setTitle(data.value)}
                                />
                            </div>
                            <Toolbar aria-label="Default">
                                <ToolbarButton
                                    aria-label="Increase Font Size"
                                    appearance="primary"
                                    icon={<FontIncrease24Regular />}
                                />
                                <ToolbarButton
                                    aria-label="Decrease Font Size"
                                    icon={<FontDecrease24Regular />}
                                />
                                <ToolbarButton aria-label="Reset Font Size" icon={<TextFont24Regular />} />
                                <ToolbarDivider />
                                <Menu>
                                    <MenuTrigger>
                                        <ToolbarButton aria-label="More" icon={<MoreHorizontal24Filled />} />
                                    </MenuTrigger>

                                    <MenuPopover>
                                        <MenuList>
                                        <MenuItem>New </MenuItem>
                                        <MenuItem>New Window</MenuItem>
                                        <MenuItem disabled>Open File</MenuItem>
                                        <MenuItem>Open Folder</MenuItem>
                                        </MenuList>
                                    </MenuPopover>
                                </Menu>
                            </Toolbar>
                            <div className="flex flex-col pb-10 pt-2">
                                <Label htmlFor={inputId}></Label>
                                <Textarea
                                    className="h-48"
                                    id={inputId}
                                    onChange={(event, data) => setContent(data.value)}
                                />
                            </div>
                    </DialogContent>
                    <DialogActions>
                        <DialogTrigger>
                            <Button 
                                appearance="primary"
                                onClick={createPost}
                            >
                                Post
                            </Button>
                        </DialogTrigger>
                        <DialogTrigger>
                            <Button 
                                appearance="secondary"
                                onClick={() => setIsDisplayed(false)}
                            >
                                Cancel
                            </Button>
                        </DialogTrigger>
                    </DialogActions>
                </DialogBody>
            </DialogSurface>
        </Dialog>
    );
}