import React, { useEffect, useState } from "react";
import { getOwnerPetList } from "../../client";
import PetDetails from "../../Dogs/DogDetailsDisplay/PetDetails";

const OwnerPetList = (owner) => {
  const [petList, setPetList] = useState([]);

  const fetchOwner = (ownerId) =>
    getOwnerPetList(ownerId)
      .then((res) => res.json())
      .then((data) => {
        setPetList(data);
      });

  useEffect(() => {
    fetchOwner(owner.owner.id);
  }, []);

  return(
  <div className="flex-col w-full">
    
    {petList?.map((pet) => {
      return <PetDetails pet={pet}/>
    })}
  </div>)
};

export default OwnerPetList;
