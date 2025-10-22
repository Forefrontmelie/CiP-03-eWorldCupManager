const Button = ({ text, className, onClick}) => {
    return (
        <input 
            type="button"             
            className={`btn btn-success ${className}`} 
            value={text} 
            onClick={onClick} 
        />
    );
}

export default Button;