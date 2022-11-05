
import { Avatar, Menu } from "antd";
import React, { useEffect, useState } from "react";
import {
  CarryOutOutlined,
  UserOutlined,
  FileOutlined,
} from "@ant-design/icons";
import PetDetails from "./DogDetailsDisplay/PetDetails";
import VisitsDetails from "./DogDetailsDisplay/VisitDetails/VisitsDetails";
import OwnerDetails from "./DogDetailsDisplay/PetDisplayOwnerDetails";
import { getEntry } from "../client";
import { useSearchParams } from "react-router-dom";
import {isMobile} from 'react-device-detect';

const items = [
  {
    label: "Pet Details",
    key: 0,
    icon: <FileOutlined />,
  },
  {
    label: "Owner Details",
    key: 1,
    icon: <UserOutlined />,
    
  },
  {
    label: "Visits",
    key: 2,
    icon: <CarryOutOutlined />,
  },
];

const itemsMobile = [
  {
    label: "Pet",
    key: 0,
    icon: <FileOutlined />,
  },
  {
    label: "Owner ",
    key: 1,
    icon: <UserOutlined />,
    
  },
  {
    label: "Visits",
    key: 2,
    icon: <CarryOutOutlined />,
  },
];

const DogDetails = (state) => {

  const [current, setCurrent] = useState("0");
  const [displayContent, setDisplayContent] = useState(0)
  const [pet, setPet] = useState("")
  const [searchParams] = useSearchParams();
  const petId = searchParams.get("petId") //getting the value from the query param from URL
  

  const fetchPet = (petId) =>
        getEntry("pets", petId)
            .then(res => res.json())
            .then(data => {
                setPet(data);
            })

    useEffect(() => {
        fetchPet(petId);
    }, []);

  const PageDisplay = () => {
    if (displayContent == 0) {
      return <PetDetails pet={pet}/>;
    } else if (displayContent == 1) {
      return <OwnerDetails pet={pet}/>
    } else {
      return <VisitsDetails pet={pet}/>;
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
      <div className=" h-1/5 bg-grey-300 pt-4">
        <div className="h-full flex flex-col justify-end items-center">
          <Avatar size={128} icon={<UserOutlined />} />
          <h1>{}</h1>
        </div>
      </div>
      {/* End avatar section */}
      <div className="flex justify-center">
        <Menu
          onClick={onClick}
          selectedKeys={[current]}
          mode="horizontal"
          items={isMobile?itemsMobile:items}
        />
      </div>
      <div className="w-full flex justify-center p-2 pt-4">
      {PageDisplay()}
      </div>
    </div>
  );
};

export default DogDetails;
