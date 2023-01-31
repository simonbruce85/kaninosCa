import React from "react";
import PetDetails from "../../Pets/DogDetailsDisplay/PetDisplayDetails";

const OwnerPetList = ({ owner }) => {

  return (
    <div className="flex-col w-full">
      {owner.pets?.map((pet) => {
        return <PetDetails pet={pet} />
      })}
    </div>)
};

export default OwnerPetList;
