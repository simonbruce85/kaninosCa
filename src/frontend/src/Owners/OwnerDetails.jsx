import { Avatar, Menu } from "antd";
import React, { useEffect, useState } from "react";
import { UserOutlined } from "@ant-design/icons";
import {
  AppstoreOutlined,
  MailOutlined,
  SettingOutlined,
} from "@ant-design/icons";
import { getEntry } from "../client";
import { useSearchParams } from "react-router-dom";
import OwnerInformation from "./OwnerDetailsDisplay/OwnerInformation";
import OwnerPetList from "./OwnerDetailsDisplay/OwnerPetList";




const items = [
  {
    label: "Owner Info",
    key: 0,
    icon: <MailOutlined />,
  },
  {
    label: "Pet List",
    key: 1,
    icon: <AppstoreOutlined />,
    
  },
];

const OwnerDetails = (state) => {

  const [current, setCurrent] = useState("0");
  const [displayContent, setDisplayContent] = useState(0)
  const [owner, setOwner] = useState("")
  const [searchParams] = useSearchParams();
  const ownerId = searchParams.get("ownerId") //getting the value from the query param from URL

  const fetchOwner = (ownerId) =>
        getEntry("owners", ownerId)
            .then(res => res.json())
            .then(data => {
                setOwner(data);
            })

    useEffect(() => {
        fetchOwner(ownerId);
    }, []);

  const PageDisplay = () => {
    if (displayContent == 0) {
      return <OwnerInformation owner={owner}/>;
    }  else {
      return <OwnerPetList owner={owner}/>;
    }
  };

  {/*Set page number after clicking one of the menu items to render the corresponding component */}
  const onClick = (e) => {
    setCurrent(e.key);
    setDisplayContent(e.key);
  };

  return (
    
    <div className="w-full min-h-screen ">
      {/* Avatar section */}
      <div className=" h-1/5 bg-grey-300">
        <div className="h-full flex flex-col justify-end items-center">
          <Avatar size={128} icon={<UserOutlined />} />
          <h1>{}</h1>
        </div>
      </div>
      {/* End avatar section */}
      <div className="w-full flex justify-center  ">
        <Menu
          onClick={onClick}
          selectedKeys={[current]}
          mode="horizontal"
          items={items}
        />
      </div>
      <div className="w-full flex justify-center">
      {PageDisplay()}
      </div>
    </div>
  );
};

export default OwnerDetails;