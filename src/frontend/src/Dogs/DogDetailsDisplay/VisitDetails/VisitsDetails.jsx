import { Badge, Button, Descriptions, Select } from "antd";
import React, { useEffect, useState } from "react";
import { getEntry } from "../../../client";
import AddNewVisitForm from "./AddNewVisitForm";
import VisitCard from "./VisitCard";
import {v4 as uuidv4} from 'uuid';

const { Option } = Select;

const VisitsDetails = (pet) => {
  const [showDrawer, setShowDrawer] = useState(false);
  const [petVisit, setPetVisit] = useState("")

  const fetchPetVisits = () =>{
    getEntry("pets", pet.pet.id)
        .then(res => res.json())
        .then(data => {
          setPetVisit(data);
          console.log(data)
        })
    }
        
useEffect(() => {
  fetchPetVisits();
}, []);

  return (
     <div className="flex w-full py-2">
      <div>
        <Descriptions
          title=""
          className="w-full"
          column={1}
        ></Descriptions>
        <div className="flex items-center">
          <Button
            onClick={() => setShowDrawer(!showDrawer)}
            type="default"
            shape="round"
            size="small"
            className="flex items-center justify-center"
          >
            Add New Visit
          </Button>
          <Badge count={pet.length} className="site-badge-count-4" />
        </div>
        <AddNewVisitForm
          showDrawer={showDrawer}
          setShowDrawer={setShowDrawer}
          petId={pet.pet.id}
          fetchPetVisits={fetchPetVisits}
        />
        <div>
          {petVisit.visits?.map((visit) => (
            <VisitCard key={visit.visitId} visit={visit}/>))}
        </div>
        
      </div> 
     </div>
  );
};

export default VisitsDetails;
