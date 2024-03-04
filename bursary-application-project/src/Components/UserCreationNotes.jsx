import {Col, Stack} from "react-bootstrap";

const UserCreationNotes = () =>{
    return(
            <Stack direction={"vertical"} className={"fw-bold"}>
                <h2>User Creation Rules And Considerations</h2>
                <ul>
                    <li className={"p-2"}>Chief's Names Slot Should Be The Chief Governing Location <br />
                        e.g Chief Kimathi Location</li>
                    <li className={"p-2"}>The Privileged Users' Default Passwords are Their Roles <br />
                        e.g viewer for A viewer user
                        form viewer.</li>
                    <li className={"p-2"} >
                        Privileged Users Should Ensure They Change Their Default Password from the <br />
                        from the default ones.
                    </li>
                    <li className={"p-2"} >
                        Privileged Users Are Emailed After Their Accounts Are Created.
                    </li>
                </ul>
            </Stack>
    )
}


export default UserCreationNotes;